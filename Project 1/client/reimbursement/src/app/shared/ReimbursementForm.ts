export class ReimbursementForm {
  amount: number;
  category: number;
  username: string;
  password: string;
  image: string;

  constructor(amount: number, category: number, username: string, password: string, image: string) {
    this.amount = amount;
    this.category = category;
    this.username = username;
    this.password = password;
    this.image = image;
  }
}
