import { Injectable, signal } from '@angular/core';

export interface ToastState {
  message: string;
  type: 'success' | 'error' | '';
  show: boolean;
}

@Injectable({ providedIn: 'root' })
export class ToastService {
  state = signal<ToastState>({ message: '', type: '', show: false });
  private timer: ReturnType<typeof setTimeout> | null = null;

  show(message: string, type: 'success' | 'error' | '' = '') {
    if (this.timer) clearTimeout(this.timer);
    this.state.set({ message, type, show: true });
    this.timer = setTimeout(() => {
      this.state.update(s => ({ ...s, show: false }));
    }, 2800);
  }
}
