export interface User {
  id: number;
  name: string;
  email: string;
  age?: number;
}

export interface CreateUserDto {
  name: string;
  email: string;
  age?: number;
}
