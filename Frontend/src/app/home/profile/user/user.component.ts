import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  validateForm: FormGroup;
  private user = null;

  constructor(private fb: FormBuilder, private router: Router, private userService: UserService) { }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      name: [null, [Validators.required]],
      surname: [null, [Validators.required]],
      username: [{value: null, disabled: true }, [Validators.required]],
      phone: [null, [Validators.required]]
    });
    this.setupUser();
    this.getDetails();
  }

  private setupUser(): void{
    this.user = JSON.parse(localStorage.getItem('user'));
  }

  private getDetails(): void{
    this.userService.getUser(this.user.id).subscribe(data =>{
      const formValues = {
        name: data.name,
        surname: data.surname,
        username: data.username,
        phone: data.phone,
      }
      this.validateForm.setValue(formValues);
    });
  }

  submitForm(): void{
    const body = {
      ...this.validateForm.value,
      id: this.user.id
    }
    this.userService.updateUser(body).subscribe(() => {
      this.router.navigateByUrl('home');
    });
  }
}
