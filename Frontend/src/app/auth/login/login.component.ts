import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { FilmService } from 'src/app/services/film.service';
import { ProjectionService } from 'src/app/services/projection.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  validateForm: FormGroup;
  items = [];
  items2 = [];
  isFilms: boolean;
  isSearch: boolean;
  isProjections: boolean;
  cinemaName = '';
  filmName = '';
  genre = '';

  constructor(private fb: FormBuilder, private router: Router, private userService: UserService, private filmService: FilmService, private projectionService: ProjectionService) {}

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      username: [null, [Validators.required]],
      password: [null, [Validators.required]],
      remember: [true]
    });
    this.filmService.getFilms().subscribe(data => {
      this.items = data;
    });
    // var data1 = {name : "TAXI", age : 18};
    // var data2 = {name : "HARRY POTTER", age : 18};
    // this.items.push(data1);
    // this.items.push(data2);
    this.isFilms = true;
    this.isProjections = false;
    this.isSearch = false;
  }

  submitForm(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }

    this.userService.login(this.validateForm.value).subscribe(data => {
      localStorage.setItem('user', JSON.stringify(data));
      this.router.navigateByUrl(`/home`);
    });
  }

  fun(duration): String{
    return duration + " min";
  }

  onRegistration(): void {
    this.router.navigateByUrl('registration');
  }

  // projections(id): void {
  //   this.projectionService.getAllProjectionsByFilm(id).subscribe(data => {
  //     this.items2 = data;
  //   })
  //   this.isFilms = false;
  // }

  backToFilms(): void{
    this.isFilms = true;
    this.isProjections = false;
    this.isSearch = false;
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

  search(): void{
    this.isFilms = false;
    this.isSearch = true;
  }

  finalSearch(): void{
    this.isProjections = true;
    this.isSearch = false;
    const filteredObject = {
      cinemaName: this.cinemaName,
      filmName: this.filmName,
      genre: this.genre
    }
    this.projectionService.getProjectionsBySearch(filteredObject).subscribe(data => {
      this.items2 = data;
    })
  }
}

