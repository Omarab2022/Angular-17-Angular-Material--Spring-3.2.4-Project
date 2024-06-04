import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { PaymentType } from '../../../Models/students.model';
import { emit } from 'process';
import { StudentsService } from '../../Services/students.service';
import { PDFDocumentProxy } from 'pdfjs-dist/types/src/display/api';

@Component({
  selector: 'app-new-payment',
  templateUrl: './new-payment.component.html',
  styleUrls: ['./new-payment.component.css']
})
export class NewPaymentComponent implements OnInit {



  paymentformgroupe!: FormGroup;
  studentCode!: string;
  paymentTypes : string[] =[];
  pdfFileUrl! : string;

  constructor(private fb: FormBuilder, private activatedroute: ActivatedRoute ,
  private studentservice : StudentsService) {}

  ngOnInit(): void {

    for(let element in PaymentType){

      let value = PaymentType[element] ;

      if(typeof(value) === 'string' ){
        this.paymentTypes.push(value);
      }


      
    }

    this.studentCode = this.activatedroute.snapshot.params['studentCode'];
    this.paymentformgroupe = this.fb.group({
      date: this.fb.control(''),
      amount: this.fb.control(''),
      type: this.fb.control(''),
      studentCode: this.fb.control(this.studentCode),
      fileSource: this.fb.control(''),
      fileName: this.fb.control(''),
    });
  }


  selectFile(event: any) {
   
    if (event.target.files.length>0) {
      let file  = event.target.files[0];

      this.paymentformgroupe.patchValue({
        fileSource:file,
        fileName : file.name,
      });

      this.pdfFileUrl = window.URL.createObjectURL(file);
      
    }
    }


    savepayment() {

      let date = new Date(this.paymentformgroupe.value.date);
      let formatteddate = date.getDate()+"/"+(date.getMonth()+1)+"/"+date.getFullYear();

      let formdata = new FormData();
      formdata.set('date',formatteddate);
      formdata.set('amount',this.paymentformgroupe.value.amount);
      formdata.set('type',this.paymentformgroupe.value.type);
      formdata.set('studentCode',this.paymentformgroupe.value.studentCode);
      formdata.set('file',this.paymentformgroupe.value.fileSource);

      this.studentservice.savePayment(formdata).subscribe({
        next : value =>{
          alert('payment saved successfully ')

        },
        error : err=>{
          console.log(err)
        }
      })

    
      }


      afterLoadComplete(event: any) {
       
        console.log(event);

        }
}
