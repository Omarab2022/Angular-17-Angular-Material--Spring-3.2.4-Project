export interface Student {

    id:string;
    code:string;
    firstName:string;
    lastName:string;
    programId:string;
    photo:string;

}

export interface Payment {
    id :number;
    date : string ;
    amount : number;
    type : string;
    status : string;
    file:string;
    student : Student;
}

export enum PaymentType{
    CASH = 0,
	DEPOSIT =1,
	CHECK =2 ,
	TRANSFER = 3
}

export enum PaymentStatus{
    CREATED,VALIDATED,REJECTED
}