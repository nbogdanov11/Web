import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { CinemaService } from 'src/app/services/cinema.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-cinema',
  templateUrl: './cinema.component.html',
  styleUrls: ['./cinema.component.css']
})
export class CinemaComponent implements OnInit {

  validateForm: FormGroup;
  private id = null
  update: boolean;
  manager = null;
  items = [];

  constructor(private fb: FormBuilder, private router: Router, private route: ActivatedRoute, private cinemaService: CinemaService, private userService: UserService) { }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      name: [null, [Validators.required]],
      address: [null, [Validators.required]],
      email: [null, [Validators.required]],
      phone: [null, [Validators.required]]
    });
    this.extractId();
    this.update = false;
    if(this.id != undefined){
      this.getDetails();
      this.update = true;
    }else{
      this.setupManagers();
    }
  }

  private extractId(): void{
    this.id = this.route.snapshot.params.id;
  }

  private getDetails(): void{
    this.cinemaService.getCinema(this.id).subscribe(data =>{
      const formValues = {
        name: data.name,
        address: data.address,
        phone: data.phone,
        email: data.email,
      }
      this.validateForm.setValue(formValues);
    });
  }

  private setupManagers(): void{
    this.userService.getManagers().subscribe(data => {
      this.items = data;
    });
  }

  submitForm(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
    }
    if(this.update){
      const body = {
        ...this.validateForm.value,
        id: this.id
      }
      this.cinemaService.updateCinema(body).subscribe(() => {
        this.router.navigateByUrl('home/cinemas');
      });
    }else{
      const body = {
        ...this.validateForm.value,
        managerId: this.manager
      }
      console.log(body);
      
      this.cinemaService.createCinema(body).subscribe(() => {
        this.router.navigateByUrl('home/cinemas');
      })
    }
  }

  managersCredential(name, surname): String{
    return name + ' ' + surname;
  }

  confirmationValidator = (control: FormControl): { [s: string]: boolean } => {
    if (!control.value) {
      return { required: true };
    }
    return {};
  };

  getCaptcha(e: MouseEvent): void {
    e.preventDefault();
  }
}
