import { Component, OnInit, inject, signal } from '@angular/core';
import { AddUserSheetComponent } from './components/add-user-sheet/add-user-sheet.component';
import { UserService } from './services/user.service';
import { ToastService } from './services/toast.service';
import { User } from './models/user.model';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [AddUserSheetComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  private userService  = inject(UserService);
  protected toastSvc   = inject(ToastService);

  users       = signal<User[]>([]);
  loading     = signal(true);
  hasError    = signal(false);
  sheetOpen   = signal(false);
  deletingIds = signal(new Set<number>());

  ngOnInit() { this.fetchUsers(); }

  get countText(): string {
    if (this.loading())  return 'Načítám...';
    if (this.hasError()) return 'Chyba připojení';
    const n = this.users().length;
    if (n === 0) return 'Žádní uživatelé';
    if (n === 1) return '1 uživatel';
    return `${n} uživatelů`;
  }

  initials(name: string): string {
    return name.trim().split(/\s+/).map(w => w[0]).join('').slice(0, 2).toUpperCase() || '?';
  }

  fetchUsers() {
    this.loading.set(true);
    this.hasError.set(false);
    this.userService.getAll().subscribe({
      next: users => { this.users.set(users); this.loading.set(false); },
      error: ()   => { this.loading.set(false); this.hasError.set(true); }
    });
  }

  deleteUser(id: number) {
    this.deletingIds.update(s => new Set([...s, id]));
    this.userService.delete(id).subscribe({
      next: () => {
        this.fetchUsers();
        this.toastSvc.show('Uživatel smazán', 'success');
      },
      error: () => {
        this.deletingIds.update(s => { const n = new Set(s); n.delete(id); return n; });
        this.toastSvc.show('Nepodařilo se smazat uživatele', 'error');
      }
    });
  }

  openSheet()  { this.sheetOpen.set(true); }
  closeSheet() { this.sheetOpen.set(false); }
}
