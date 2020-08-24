import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { CinemaService } from 'src/app/services/cinema.service';

@Component({
  selector: 'app-cinemas',
  templateUrl: './cinemas.component.html',
  styleUrls: ['./cinemas.component.css']
})
export class CinemasComponent implements OnInit {

  items = [];
  private id = null;
  private user = null;
  manager: boolean;

  constructor(private router: Router, private cinemaService: CinemaService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.extractId();
    this.setupUser();
    if(this.id == undefined){
      this.setupData1();
    }else{
      this.setupData2();
    }
  }

  private extractId(): void{
    this.id = this.route.snapshot.params.id;
  }

  private setupUser(): void{
    this.user = JSON.parse(localStorage.getItem('user'));
    if(this.user.role === 'MANAGER'){
      this.manager = true;
    }else{
      this.manager = false;
    }
  }

  private setupData1(): void{
    this.cinemaService.getCinemas().subscribe(data => {
      this.items = data;
    });
  }

  private setupData2(): void{
    this.cinemaService.getAllCinemasByManager(this.user.id).subscribe(data => {
      this.items = data;
    });
  }

  updateCinema(id): void{
    this.router.navigateByUrl(`home/cinema/${id}`);
  }

  deleteCinema(id): void{
    this.cinemaService.deleteCinema(id).subscribe(() => {
      if(this.id == undefined){
        this.setupData1();
      }else{
        this.setupData2();
      }
    });
  }

  cinemasTheaters(id): void{
    this.router.navigateByUrl(`home/theaters/${id}/cinema`);
  }
}
