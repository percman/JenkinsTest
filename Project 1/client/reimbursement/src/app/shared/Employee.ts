export class Employee {
  username: string;
  password: string;
  eid: number;
  isManager: boolean;
  firstName: string;
  lastName: string;
  address: string;

  constructor(username: string, password: string, eid: number, isManager: boolean, firstName: string, lastName: string, address: string) {
    this.username = username;
    this.password = password;
    this.eid = eid;
    this.isManager = isManager;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
  }
}
