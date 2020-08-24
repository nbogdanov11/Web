import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-viewers',
  templateUrl: './viewers.component.html',
  styleUrls: ['./viewers.component.css']
})
export class ViewersComponent implements OnInit {

  items = [];

  constructor(private router: Router, private userService: UserService) { }

  ngOnInit(): void {
    this.setupData();
  }

  private setupData(): void{
    this.userService.getViewers().subscribe(data => {
      this.items = data;
    })
  }

  deleteViewer(id): void{
    this.userService.blockUser(id).subscribe(() => {
      this.setupData();
    });
  }
}
