import { Component, output, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { ToastService } from '../../services/toast.service';

@Component({
  selector: 'app-add-user-sheet',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './add-user-sheet.component.html',
  styleUrl: './add-user-sheet.component.scss'
})
export class AddUserSheetComponent {
  readonly saved = output<void>();
  readonly closed = output<void>();

  private fb = inject(FormBuilder);
  private userService = inject(UserService);
  private toastService = inject(ToastService);

  form = this.fb.group({
    name:  ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    age:   [null as number | null]
  });

  loading = signal(false);

  get nameError(): string {
    const c = this.form.get('name')!;
    if (!c.touched) return '';
    if (c.hasError('required')) return 'Jméno je povinné';
    if (c.errors?.['serverError']) return c.errors['serverError'];
    return '';
  }

  get emailError(): string {
    const c = this.form.get('email')!;
    if (!c.touched) return '';
    if (c.hasError('required')) return 'E-mail je povinný';
    if (c.hasError('email')) return 'Neplatný formát e-mailu';
    if (c.errors?.['serverError']) return c.errors['serverError'];
    return '';
  }

  submit() {
    this.form.markAllAsTouched();
    if (this.form.invalid) return;

    this.loading.set(true);
    const { name, email, age } = this.form.value;

    this.userService.create({ name: name!, email: email!, age: age ?? undefined }).subscribe({
      next: () => {
        this.loading.set(false);
        this.saved.emit();
        this.toastService.show('Uživatel přidán ✓', 'success');
        this.reset();
        this.closed.emit();
      },
      error: (err) => {
        this.loading.set(false);
        if (err.status === 400 && err.error?.errors) {
          const errors = err.error.errors;
          if (errors.name)  this.form.get('name')!.setErrors({ serverError: errors.name });
          if (errors.email) this.form.get('email')!.setErrors({ serverError: errors.email });
        } else {
          this.toastService.show('Nepodařilo se přidat uživatele', 'error');
        }
      }
    });
  }

  close() {
    this.reset();
    this.closed.emit();
  }

  private reset() {
    this.form.reset();
    this.form.markAsUntouched();
  }
}
