import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { TheaterService } from 'src/app/services/theater.service';

@Component({
  selector: 'app-theaters',
  templateUrl: './theaters.component.html',
  styleUrls: ['./theaters.component.css']
})
export class TheatersComponent implements OnInit {

  items = [];
  private id = null;

  constructor(private router: Router, private route: ActivatedRoute, private theaterService: TheaterService) { }

  ngOnInit(): void {
    this.extractId();
    this.setupData();
  }

  private extractId(): void{
    this.id = this.route.snapshot.params.id;
  }

  private setupData(): void{
    this.theaterService.getAllTheatersByCinema(this.id).subscribe(data => {
      this.items = data;
    });
  }

  updateTheater(id): void{
    localStorage.setItem('cinemaId', JSON.stringify(this.id));
    this.router.navigateByUrl(`home/theater/${id}`);
  }

  deleteTheater(id): void{
    this.theaterService.deleteTheater(id).subscribe(() => {
      this.setupData();
    })
  }

  createTheater(): void{
    localStorage.setItem('cinemaId', JSON.stringify(this.id));
    this.router.navigateByUrl(`home/theater`);
  }
}
