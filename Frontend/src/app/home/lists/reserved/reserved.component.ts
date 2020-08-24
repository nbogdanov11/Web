import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TicketService } from 'src/app/services/ticket.service';
import { ProjectionService } from 'src/app/services/projection.service';

@Component({
  selector: 'app-reserved',
  templateUrl: './reserved.component.html',
  styleUrls: ['./reserved.component.css']
})
export class ReservedComponent implements OnInit {

  items = [];
  private user = null;

  constructor(private router: Router, private ticketService: TicketService) { }

  ngOnInit(): void {
    this.setupUser();
    this.setupData();
  }

  private setupUser(): void{
    this.user = JSON.parse(localStorage.getItem('user'));
  }

  private setupData(): void{
    this.ticketService.getAllReservedTicketsByViewer(this.user.id).subscribe(data => {
      this.items = data;
    });
  }

  pay(id): void{
    const body = {
      
    }
    this.ticketService.payTicket(id, body).subscribe(() => {
      this.setupData();
    });
  }

  cancel(id): void{
    const body = {

    }
    this.ticketService.cancelTicket(id, body).subscribe(() => {
      this.setupData();
    });
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
}
