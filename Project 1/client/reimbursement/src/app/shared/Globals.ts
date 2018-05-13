import {Injectable} from '@angular/core';


@Injectable()
export class Globals {
  username = '';
  password = '';
  eid: number;
  isManager: boolean;
  firstName = '';
  lastName = '';
  address = '';
  ready = false;
}
