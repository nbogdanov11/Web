import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { TheaterService } from 'src/app/services/theater.service';

@Component({
  selector: 'app-theater',
  templateUrl: './theater.component.html',
  styleUrls: ['./theater.component.css']
})
export class TheaterComponent implements OnInit {

  validateForm: FormGroup;
  private id = null;
  private cinemaId = null;

  constructor(private router: Router, private route: ActivatedRoute, private fb: FormBuilder, private theaterService: TheaterService) { }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      name: [null, [Validators.required]],
      seats: [null, [Validators.required, Validators.pattern("^[0-9]*$")]]
    });
    this.extractId();
    this.setupCinemaId();
    if(this.id != undefined){
      this.getDetails();
    }
  }

  private extractId(): void{
    this.id = this.route.snapshot.params.id;
  }

  private getDetails(): void{
    this.theaterService.getTheater(this.id).subscribe(data =>{
      const formValues = {
        name: data.name,
        seats: data.seats
      }
      this.validateForm.setValue(formValues);
    });
  }

  private setupCinemaId(): void{
    this.cinemaId = JSON.parse(localStorage.getItem('cinemaId'));
  }

  submitForm(): void{
    if(this.id != undefined){
      const body = {
        ...this.validateForm.value,
        id: this.id,
        cinemaId: this.cinemaId
      }
      this.theaterService.updateTheater(body).subscribe(() => {
        this.router.navigateByUrl(`home/theaters/${this.cinemaId}/cinema`);
      })
    }else{
      const body = {
        ...this.validateForm.value,
        cinemaId: this.cinemaId
      }
      this.theaterService.createTheater(body).subscribe(() => {
        this.router.navigateByUrl(`home/theaters/${this.cinemaId}/cinema`);
      })
    }
  }
}
