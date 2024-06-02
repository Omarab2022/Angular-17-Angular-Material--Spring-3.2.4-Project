import { HttpClient } from '@angular/common/http';
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { StudentsService } from '../../Services/students.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrl: './payment.component.css'
})
export class PaymentComponent implements OnInit{



  public payments : any ;

  public displayedColumns = ["id", "date", "amount","type","status","firstName"];

  public datasource :any ;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;



  constructor( private studentService : StudentsService){

  }
 

  ngOnInit(): void {

    this.studentService.getAllPayments().subscribe( {

      next: value => {

        this.payments = value;

        this.datasource = new MatTableDataSource(this.payments);

        this.datasource.paginator = this.paginator;
        this.datasource.sort = this.sort;

      },

      error: (error) => {
        console.log(error);
      }
    });
    
  }

  searchPayments(event: Event) {
    let value = (event.target as HTMLInputElement).value;
      this.datasource.filter = value.trim().toLowerCase();
  }


}







