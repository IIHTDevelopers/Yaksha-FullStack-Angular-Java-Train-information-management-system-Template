import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Train } from '../model/train';

@Injectable({
  providedIn: 'root'
})
export class TrainService {
  private baseUrl = '';

  constructor(private http: HttpClient) { }

  createTrain(train: Train) {
    //write your logic here
    return null;
  }

  updateTrain(train: Train){
    //write your logic here
    return null;
  }

  deleteTrain(id: number){
    //write your logic here
    return null;
  }

  getTrainById(id: number){
    //write your logic here
    return null;
  }

  getAllTrains(){
    //write your logic here
    return null;
  }

  getTrainByNumber(number: number){
    //write your logic here
    return null;
  }

  getTrainsByName(name: string){
    //write your logic here
    return null;
  }

  getTrainsBySeatsAvailable(seatsAvailable: number){
    //write your logic here
    return null;
  }

}
