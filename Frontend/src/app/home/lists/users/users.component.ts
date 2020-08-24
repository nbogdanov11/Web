import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  items = [];
  private id = null;
  active: boolean;

  constructor(private router: Router, private route: ActivatedRoute, private userService: UserService) { }

  ngOnInit(): void {
    this.extractId();
    if(this.id != undefined){
      this.setupData1();
    }else{
      this.setupData2();
    }
  }

  private extractId(): void{
    this.id = this.route.snapshot.params.id;
  }

  private setupData1(): void{
    this.userService.getManagers().subscribe(data => {
      this.items = data;
    });
    this.active = true;
  }

  private setupData2(): void{
    this.userService.getInactiveManagers().subscribe(data => {
      this.items = data;
    });
    this.active = false;
  }

  deactivate(id): void{
    this.userService.blockUser(id).subscribe(() => {
      this.setupData1();
    });
  }

  activate(id): void{
    const body = {

    }
    this.userService.activateManager(id, body).subscribe(() => {
      this.setupData2();
    })
  }
}
