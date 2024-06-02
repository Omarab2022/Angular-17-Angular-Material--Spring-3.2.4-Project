import { HttpClient } from '@angular/common/http';
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';


@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrl: './students.component.css'
})
export class StudentsComponent implements OnInit ,AfterViewInit{


  constructor(private router: Router , private http :HttpClient) { }


  public students : any;

  public datasource :any ;

  public displayedColumns = ["id", "name", "lastname","code","email" , "photo"  , "Payment"];

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;


  ngOnInit(): void {
    

    this.students = [];

    this.http.get('http://localhost:8080/students').subscribe( {
      next : value => {
        this.students = value;

        this.datasource = new MatTableDataSource(this.students);

        this.datasource.paginator = this.paginator;

        this.datasource.sort = this.sort;


      },
      error: (error) => {
        console.log(error);
      }
      });

    this.datasource = new MatTableDataSource(this.students);
  }


  ngAfterViewInit(): void {

    this.datasource.paginator = this.paginator;
    this.datasource.sort = this.sort;
  
  }


  searchStudents(event: Event) {
   
    let value = (event.target as HTMLInputElement).value;
    this.datasource.filter = value.trim().toLowerCase();

    }


    getpayments(student: any) {
      this.router.navigateByUrl("/payment")
      }
  }


