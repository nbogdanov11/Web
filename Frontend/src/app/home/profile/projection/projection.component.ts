import { Component, OnInit } from '@angular/core';
import { FilmService } from 'src/app/services/film.service';
import { TheaterService } from 'src/app/services/theater.service';
import { Router } from '@angular/router';
import { ProjectionService } from 'src/app/services/projection.service';

@Component({
  selector: 'app-projection',
  templateUrl: './projection.component.html',
  styleUrls: ['./projection.component.css']
})
export class ProjectionComponent implements OnInit {

  filmId = null;
  theaterId = null;
  time = null;
  price = null;
  items1 = [];
  items2 = [];
  private user = null;

  constructor(private router: Router, private filmService: FilmService, private theaterService: TheaterService, private projectionService: ProjectionService) { }

  ngOnInit(): void {
    this.setupUser();
    this.setupData1();
    this.setupData2();
  }

  private setupUser(): void{
    this.user = JSON.parse(localStorage.getItem('user'));
  }

  private setupData1(): void{
    this.filmService.getFilms().subscribe(data => {
      this.items1 = data;
    });
  }

  private setupData2(): void{
    this.theaterService.getAllTheatersByManager(this.user.id).subscribe(data => {
      this.items2 = data;
    })
  }

  selectTheater(name, cinemaName): String {
     return cinemaName + ' ' + name;
  }

  submit(): void{
    const body = {
      filmId: this.filmId,
      theaterId: this.theaterId,
      time: this.time,
      price: this.price
    }
    this.projectionService.createProjection(body).subscribe(() => {
      this.router.navigateByUrl(`home`);
    });
  }
}
