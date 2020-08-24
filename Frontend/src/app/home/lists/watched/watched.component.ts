import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ProjectionService } from 'src/app/services/projection.service';
import { RateService } from 'src/app/services/rate.service';
import { TicketService } from 'src/app/services/ticket.service';
import { NzMessageService } from 'ng-zorro-antd';

@Component({
  selector: 'app-watched',
  templateUrl: './watched.component.html',
  styleUrls: ['./watched.component.css']
})
export class WatchedComponent implements OnInit {

  items = [];
  private user = null;
  private id = null;
  graded: boolean;
  show: boolean;
  grade: any;

  constructor(private message: NzMessageService, private router: Router, private projectionService: ProjectionService, private rateService: RateService, private route: ActivatedRoute, private ticketService: TicketService) { }

  ngOnInit(): void {
    this.show = false;
    this.grade = 5;
    this.setupUser();
    this.extractId();
    if(this.id == undefined){
      this.graded = false;
      this.setupData1();
    }else{
      this.graded = true;
      this.setupData2();
    }
  }

  private setupUser(): void{
    this.user = JSON.parse(localStorage.getItem('user'));
  }

  private extractId(): void{
    this.id = this.route.snapshot.params.id;
  }

  private setupData1(): void{
    this.ticketService.getAllUnratedFilmsByViewer(this.user.id).subscribe(data => {
      this.items = data;
    });
  }

  private setupData2(): void{
    this.rateService.getAllRatedFilmsByViewer(this.user.id).subscribe(data => {
      this.items = data;
    });
  }

  rate(id): void{
    const body = {
      rate: this.grade,
      filmId: id,
      viewerId: this.user.id
    }
    this.rateService.rateFilm(body).subscribe(() => {
      this.show = false;
      this.setupData1();
    });
  }

  showStars(): void{
    this.show = true;
  }

  getAvgRating(id): void{
    this.rateService.getFilmsRating(id).subscribe(data => {
      this.message.info('Prosecna ocena je: ' + data.rating);
    });
  }

  duration(duration): String{
    return duration + ' min';
  }
}
