import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  isCollapsed = null;
  admin: boolean;
  manager: boolean;
  viewer: boolean;
  private user = null;

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.admin = false;
    this.manager = false;
    this.viewer = false;
    this.setupUser();
    this.setupUserRole();
  }

  private setupUser(): void{
    this.user = JSON.parse(localStorage.getItem('user'));
  }

  private setupUserRole(): void{
    if(this.user.role === 'ADMINISTRATOR'){
      this.admin = true;
    }else if(this.user.role === 'MANAGER'){
      this.manager = true;
    }else if(this.user.role === 'VIEWER'){
      this.viewer = true;
    }
  }

  // VIEWER

  getProjectionsByViewer(): void{
    this.router.navigateByUrl('home/projections');
  }

  reservations(): void{
    this.router.navigateByUrl('home/reserved-tickets');
  }

  unratedFilms(): void{
    this.router.navigateByUrl(`home/ungraded-films`);
  }

  ratedFilms(): void{
    this.router.navigateByUrl(`home/graded-films/${this.user.id}/viewer`);
  }

  // VIEWER



  // ADMIN

  allCinemas(): void{
    this.router.navigateByUrl(`home/cinemas`);
  }

  createCinema(): void{
    this.router.navigateByUrl(`home/cinema`);
  }

  activeManagers(): void{
    this.router.navigateByUrl(`home/users/${this.user.id}`);
  }

  inactiveManagers(): void{
    this.router.navigateByUrl(`home/users`);
  }

  allViewers(): void{
    this.router.navigateByUrl('home/viewers');
  }

  // ADMIN



  // MANAGER

  managersCinemas(): void{
    this.router.navigateByUrl(`home/cinemas/${this.user.id}/manager`);
  }

  addProjection(): void{
    this.router.navigateByUrl(`home/projection`);
  }

  // MANAGER

  profile(): void{
    this.router.navigateByUrl('home/user');
  }

  logout(): void{
    localStorage.clear();
    this.router.navigateByUrl(`login`);
  }
}
