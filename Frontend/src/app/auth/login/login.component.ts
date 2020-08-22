import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { FilmService } from 'src/app/services/film.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  validateForm: FormGroup;
  items = [];

  constructor(private fb: FormBuilder, private router: Router, private userService: UserService, private filmService: FilmService) {}

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      username: [null, [Validators.required]],
      password: [null, [Validators.required]],
      remember: [true]
    });
    this.filmService.getFilms().subscribe(data => {
      this.items = data;
    })
    // var data1 = {name : "TAXI", age : 18};
    // var data2 = {name : "HARRY POTTER", age : 18};
    // this.items.push(data1);
    // this.items.push(data2);
  }

  submitForm(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }

    this.userService.login(this.validateForm.value).subscribe(data => {
      localStorage.setItem('user', JSON.stringify(data));
    });
  }

  fun(duration): String{
    return duration + " min";
  }

  onRegistration(): void {
    this.router.navigateByUrl('registration');
  }

}

