import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StudentsService } from '../../Services/students.service';
import { Payment } from '../../../Models/students.model';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-student-details',
  templateUrl: './student-details.component.html',
  styleUrl: './student-details.component.css'
})
export class StudentDetailsComponent implements OnInit{

  

  studentCode! : string;

  studentPayment! : Array<Payment>;

  paymentdatasource! : MatTableDataSource<Payment>


  public displayedColumns = ["id", "date", "amount","type","status","firstName"];
  
  constructor(private activatedroute : ActivatedRoute , public studentservice : StudentsService ,
  public router : Router){

  }

  
  ngOnInit(): void {

    this.studentCode = this.activatedroute.snapshot.params['code'];

    this.studentservice.getStudentPayment(this.studentCode).subscribe({

      next:value=>{

        this.studentPayment=value;

        this.paymentdatasource=new MatTableDataSource<Payment>(this.studentPayment);


      },
      error: err =>{

      console.log(err);
      }


    });
   
  }


  newpayment() {
    this.router.navigateByUrl("/admin/new-payment/"+this.studentCode)
    }

  searchPayments(event: Event) {
    let value = (event.target as HTMLInputElement).value;
      this.paymentdatasource.filter = value.trim().toLowerCase();
  }

}
