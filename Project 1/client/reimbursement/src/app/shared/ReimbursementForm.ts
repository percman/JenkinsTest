export class ReimbursementForm {
  amount: number;
  category: number;
  username: string;
  password: string;

  constructor(amount: number, category: number, username: string, password: string) {
    this.amount = amount;
    this.category = category;
    this.username = username;
    this.password = password;
  }
}
