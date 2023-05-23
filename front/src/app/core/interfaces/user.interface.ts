export interface User {
  id: number;
  username: string;
  email: string;
  password: string;
  created_date?: Date;
  updated_date?: Date;
}
