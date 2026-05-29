import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User, CreateUserDto } from '../models/user.model';

@Injectable({ providedIn: 'root' })
export class UserService {
  private http = inject(HttpClient);
  private readonly api = '/api/users';

  getAll(): Observable<User[]> {
    return this.http.get<User[]>(this.api);
  }

  create(dto: CreateUserDto): Observable<User> {
    return this.http.post<User>(this.api, dto);
  }

  delete(id: number): Observable<void> {
    return this.http.post<void>(`${this.api}/${id}/delete`, {});
  }
}
