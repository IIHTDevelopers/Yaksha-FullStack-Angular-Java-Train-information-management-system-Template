import { HttpParams } from '@angular/common/http';
import { of } from 'rxjs';
import { TrainService } from './train.service';

describe('TrainService', () => {
  let service: TrainService;
  let httpClientSpy: any;
  beforeEach(() => {
    httpClientSpy = {
      get: jest.fn(),
      post: jest.fn(),
      put: jest.fn(),
      delete: jest.fn(),
    };
    service = new TrainService(httpClientSpy);
  });

  describe('business', () => {
    it('service should be created', () => {
      expect(service).toBeTruthy(); 
     });

    it('should get all trains by calling getAllTrains in service', () => {
      const res = 'some message';
      const url = 'http://127.0.0.1:8081/traininfo/trains';
      jest.spyOn(httpClientSpy, 'get').mockReturnValue(of(res)); 
      service.getAllTrains();
      expect(httpClientSpy.get).toHaveBeenCalledWith(url); 
    });

    it('should get a train calling getTrainById in service', () => {
      const res = 'some message';
      const url = 'http://127.0.0.1:8081/traininfo/trains/1';
      jest.spyOn(httpClientSpy, 'get').mockReturnValue(of(res)); 
      service.getTrainById(1); 
      expect(httpClientSpy.get).toHaveBeenCalledWith(url);
    });

    it('should create the train calling createTrain in service', () => {
      const data = {
        id: 1,
        number: 1234,
        name: 'Train1',
        departureStation: 'Hyd',
        departureTime: '4:00',
        arrivalStation: 'Banglore',
        arrivalTime: '8:00',
        duration: '5',
        distance: '800 KMS',
        fare: 500,
        seatsAvailable:10
      };
      const res = 'some message';
      const url = 'http://127.0.0.1:8081/traininfo/trains';
      jest.spyOn(httpClientSpy, 'post').mockReturnValue(of(res));
      service.createTrain(data);
      expect(httpClientSpy.post).toHaveBeenCalledWith(url, data);
    });
    

    it('should update the train calling updateTrain in service', () => {
      const data = {
        id: 1,
        number: 1234,
        name: 'Train1',
        departureStation: 'Hyd',
        departureTime: '4:00',
        arrivalStation: 'Banglore',
        arrivalTime: '8:00',
        duration: '5',
        distance: '800 KMS',
        fare: 500,
        seatsAvailable:10
      };

      const res = 'some message';
      const url = 'http://127.0.0.1:8081/traininfo/trains/1';
      jest.spyOn(httpClientSpy, 'put').mockReturnValue(of(res));
      service.updateTrain(data);
      expect(httpClientSpy.put).toHaveBeenCalledWith(url, data);
    });

    it('should delete the train calling deleteTrain() in service', () => {
       const res = 'some message';
      const API_URL = 'http://127.0.0.1:8081/traininfo/trains/1';
      jest.spyOn(httpClientSpy, 'delete').mockReturnValue(of(res));
      service.deleteTrain(1);
      expect(httpClientSpy.delete).toHaveBeenCalledWith(API_URL);
    });


    it('should search train with number by calling getTrainByNumber in service', () => {
      const res = 'some message';
      const API_URL = 'http://127.0.0.1:8081/traininfo/trains';
      jest.spyOn(httpClientSpy, 'get').mockReturnValue(of(res));
      service.getTrainByNumber(1234);
      expect(httpClientSpy.get).toHaveBeenCalledWith(API_URL+'/number'+'/1234');
    });

    it('should search train with name by calling getTrainsByName in service', () => { 
      const res = 'some message';
      const API_URL = 'http://127.0.0.1:8081/traininfo/trains';
      jest.spyOn(httpClientSpy, 'get').mockReturnValue(of(res));
      service. getTrainsByName('Train1');
      expect(httpClientSpy.get).toHaveBeenCalledWith(API_URL+'/name'+'/Train1');
    });

    it('should search train with seats-available by calling getTrainsBySeatsAvailable in service', () => { 
      const res = 'some message';
      const API_URL = 'http://127.0.0.1:8081/traininfo/trains';
      jest.spyOn(httpClientSpy, 'get').mockReturnValue(of(res));
      service.getTrainsBySeatsAvailable(5);
      expect(httpClientSpy.get).toHaveBeenCalledWith(API_URL+'/seats-available'+'/5');
    });
   
  });
});


// import { TestBed } from '@angular/core/testing';

// import { TrainService } from './train.service';

// describe('TrainService', () => {
//   let service: TrainService;

//   beforeEach(() => {
//     TestBed.configureTestingModule({});
//     service = TestBed.inject(TrainService);
//   });

//   it('should be created', () => {
//     expect(service).toBeTruthy();
//   });
// });
