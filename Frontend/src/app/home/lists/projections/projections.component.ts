import { Component, OnInit } from '@angular/core';
import { ProjectionService } from 'src/app/services/projection.service';
import { Router } from '@angular/router';
import { TicketService } from 'src/app/services/ticket.service';
import { NzMessageService } from 'ng-zorro-antd';

@Component({
  selector: 'app-projections',
  templateUrl: './projections.component.html',
  styleUrls: ['./projections.component.css']
})
export class ProjectionsComponent implements OnInit {

  items = [];
  private user = null;
  isSearch: boolean = true;
  genre = '';
  filmName = '';
  cinemaName = '';

  constructor(private router: Router, private projectionService: ProjectionService, private ticketService: TicketService, private message: NzMessageService) { }

  ngOnInit(): void {
    this.setupUser();
  }

  private setupUser(): void{
    this.user = JSON.parse(localStorage.getItem('user'));
  }

  search(): void{
    const filteredObject = {
      filmName: this.filmName,
      cinemaName: this.cinemaName,
      genre: this.genre
    }
    this.projectionService.getProjectionsBySearch(filteredObject).subscribe(data => {
      this.items = data;
    });
    this.isSearch = false;
  }

  backToearch(): void{
    this.isSearch = true;
  }

  format(time): String{
    var res = time.toString().split(",", 5);
    var day = res[2];
    var mounth = res[1];
    var year = res[0];
    var hour = res[3];
    var minut = res[4];
    if(day.length == 1){
      day = '0' + res[2];
    }
    if(mounth.length == 1){
      mounth = '0' + res[1];
    }
    if(hour.length == 1){
      hour = '0' + res[3];
    }
    if(minut.length == 1){
      minut = '0' + res[4];
    }
    return day + '/' + mounth + '/' + year + ' u '+ hour + ':' + minut + 'h';
  }

  reserve(id): void{
    const body = {
      projectionId: id,
      viewerId: this.user.id
    }
    this.ticketService.reserveTicket(body).subscribe(() => {
      this.message.info('Uspesno ste rezervisali kartu.');
    });
  }
}
