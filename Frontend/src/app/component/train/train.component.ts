import { Component, OnInit } from '@angular/core';
import { FormGroup} from '@angular/forms';
import { Train } from '../../model/train';


@Component({
  selector: 'app-train',
  templateUrl: './train.component.html',
  styleUrls: ['./train.component.css']
})

export class TrainComponent implements OnInit {
  trainForm!: FormGroup;
  trains!: Train[];
  searchByNumberForm!: FormGroup;
  searchByNameForm!: FormGroup;  
  searchBySeatsAvalForm!: FormGroup;

  constructor() {
    //write your logic here
  }

  ngOnInit(): void {
    //write your logic here
  }

  getAllTrains(): void {
    //write your logic here
  }

 
  getTrainById(id: number): void {
    //write your logic here
  }

  createTrain(): void {
    //write your logic here
  }

  editTrain(train: any) {
    //write your logic here
  }

  updateTrain(train: Train): void {
    //write your logic here
  }

  deleteTrain(id: number): void {
    //write your logic here
  }

  searchByNumber(): void {
    //write your logic here
  }

  searchByName(): void {
    //write your logic here
  }

  searchBySeatsAvailable(): void {
    //write your logic here
  }


}
