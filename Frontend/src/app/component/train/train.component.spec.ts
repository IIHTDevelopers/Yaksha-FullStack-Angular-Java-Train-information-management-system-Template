import { ComponentFixture, TestBed } from "@angular/core/testing";
import { FormBuilder, FormsModule, ReactiveFormsModule } from "@angular/forms";
import { TrainComponent } from "./train.component";
import { TrainService } from "../../service/train.service";
import { HttpClientModule } from "@angular/common/http";
import {
  HttpClientTestingModule,
  HttpTestingController,
} from "@angular/common/http/testing";
import { Train } from "src/app/model/train";
import { of } from 'rxjs';

describe("TrainComponent", () => {
  let component: TrainComponent;
  let fixture: ComponentFixture<TrainComponent>;
  let serviceMock: any;
  let trainService: TrainService;
  const train: Train = {
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
    seatsAvailable: 10
  };

  let mockService = {
    getAllTrains: () => {
      return of([train]);
    },
    getTrainById: () => {
      return of([train]);
    },
    deleteTrain: (id: number | string) => {
      return of(train);
    },
    getTrainByNumber: () => {
      return of(train);
    },
    getTrainByName: () => {
      return of([train]);
    },
    getTrainsBySeatsAvailable: () => {
      return of([train]);
    }          
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TrainComponent],
      imports: [
        FormsModule,
        ReactiveFormsModule,
        HttpClientModule,
        HttpClientTestingModule,
      ],
      providers: [
        FormBuilder,
        TrainService,
        HttpTestingController,
        { provide: TrainService, useValue: mockService },
      ],
    }).compileComponents();
  });

  beforeEach(() => {
    serviceMock = {
      getAllTrains: jest.fn(),
      addTrain: jest.fn(),
      updateTrain: jest.fn(),
      deleteTrain: jest.fn(),
      searchTrains: jest.fn(),
    };

    fixture = TestBed.createComponent(TrainComponent);
    component = fixture.componentInstance;
    trainService = TestBed.inject(TrainService);
    fixture.detectChanges();
  });

  describe("business", () => {
    it("should create the train component", () => {
      expect(component).toBeTruthy();
    });

    it("should declare trainForm obj referece", () => {
      expect(component.trainForm).toBeDefined();
    });

    it("should initialize the form", () => {
      const trainForm = {
        id: "",
        number: "",
        name: "",
        departureStation: "",
        departureTime: "",
        arrivalStation: "",
        arrivalTime: "",
        duration: "",
        distance: "",
        fare: "",
        seatsAvailable: "",
      };
      expect(component.trainForm.value).toEqual(trainForm);
      const numberForm={
        number: ""
      };
      expect(component.searchByNumberForm.value).toEqual(numberForm);

      const nameForm={
        name: ""
      };
      expect(component.searchByNameForm.value).toEqual(nameForm);

      const seatsForm={
        seats: ""
      };
      expect(component.searchBySeatsAvalForm.value).toEqual(seatsForm);

    });

    it("should validate the number field in the form", () => {
      const c = component.trainForm.controls["number"];
      c.setValue(1234);
      expect(c.valid).toBeTruthy();
      c.setValue("");
      expect(c.invalid).toBeTruthy();
    });

    it("should validate the name field in the form", () => {
      const c = component.trainForm.controls["name"];
      c.setValue("Train1");
      expect(c.valid).toBeTruthy();
      c.setValue("");
      expect(c.invalid).toBeTruthy();
      c.setValue("Ve");
      expect(c.invalid).toBeTruthy();
      c.setValue("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean mas");
      expect(c.invalid).toBeTruthy();
    });

    it("should validate the departureStation field in the form", () => {
      const c = component.trainForm.controls["departureStation"];
      c.setValue('Hyderabad');
      expect(c.valid).toBeTruthy();
      c.setValue("");
      expect(c.invalid).toBeTruthy();
    });

    it("should validate the departureTime field in the form", () => {
      const c = component.trainForm.controls["departureTime"];
      c.setValue("11:10");
      expect(c.valid).toBeTruthy();
      c.setValue("");
      expect(c.invalid).toBeTruthy();
    });

    it("should validate the arrivalStation field in the form", () => {
      const c = component.trainForm.controls["arrivalStation"];
      c.setValue("Banglore");
      expect(c.valid).toBeTruthy();
      c.setValue("");
      expect(c.invalid).toBeTruthy();
    });
    it("should validate the arrivalTime field in the form", () => {
      const c = component.trainForm.controls["arrivalTime"];
      c.setValue("5:15");
      expect(c.valid).toBeTruthy();
      c.setValue("");
      expect(c.invalid).toBeTruthy();
    });
    it("should validate the duration field in the form", () => {
      const c = component.trainForm.controls["duration"];
      c.setValue("5 hrs");
      expect(c.valid).toBeTruthy();
      c.setValue("");
      expect(c.invalid).toBeTruthy();

    });

    it("should validate the distance field in the form", () => {
      const c = component.trainForm.controls["distance"];
      c.setValue("700 kms");
      expect(c.valid).toBeTruthy();
      c.setValue("");
      expect(c.invalid).toBeTruthy();
    });
    it("should validate the fare field in the form", () => {
      const c = component.trainForm.controls["fare"];
      c.setValue(750);
      expect(c.valid).toBeTruthy();
      c.setValue("");
      expect(c.invalid).toBeFalsy();//

    });
    it("should validate the seatsAvailable field in the form", () => {
      const c = component.trainForm.controls["seatsAvailable"];
      c.setValue(10);
      expect(c.valid).toBeTruthy();
      c.setValue("");
      expect(c.invalid).toBeFalsy();//
    });

    it("should validate the number field in the searchByNumberForm", () => {
      const c = component.searchByNumberForm.controls["number"];
      c.setValue(1234);
      expect(c.valid).toBeTruthy();
      c.setValue("");
      expect(c.invalid).toBeTruthy();
    });
    it("should validate the name field in the searchByNameForm", () => {
      const c = component.searchByNameForm.controls["name"];
      c.setValue("Train1");
      expect(c.valid).toBeTruthy();
      c.setValue("");
      expect(c.invalid).toBeTruthy();
    });
    it("should validate the seats field in the searchBySeatsAvalForm", () => {
      const c = component.searchBySeatsAvalForm.controls["seats"];
      c.setValue(10);
      expect(c.valid).toBeTruthy();
      c.setValue("");
      expect(c.invalid).toBeTruthy();
    });

  });

  describe("boundary", () => {
    it("should invalidate the form when name length is greater than 100", () => {
      const form = component.trainForm;
      form.controls["name"].setValue(
        "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean mas"
      );
      expect(form.invalid).toBeTruthy();
      expect(form.controls["name"].errors?.["maxlength"]).toBeTruthy();
    });

    it("should invalidate the form when name length is less than 3", () => {
      const form = component.trainForm;
      form.controls["name"].setValue("Tr");
      expect(form.invalid).toBeTruthy();
      expect(form.controls["name"].errors?.["minlength"]).toBeTruthy();
    });

    it("should validate the form on valid values to all fields", () => {
      component.trainForm.controls["id"].setValue("1");
      component.trainForm.controls["number"].setValue("1234");
      component.trainForm.controls["name"].setValue("Train1");
      component.trainForm.controls["departureStation"].setValue("Hyderabad");
      component.trainForm.controls["departureTime"].setValue("11:15 AM");
      component.trainForm.controls["arrivalStation"].setValue("Banglore");
      component.trainForm.controls["arrivalTime"].setValue("5:15 AM");
      component.trainForm.controls["duration"].setValue("6 hrs");
      component.trainForm.controls["distance"].setValue("750 kms");
      component.trainForm.controls["fare"].setValue("900");
      component.trainForm.controls["seatsAvailable"].setValue("15");
      expect(component.trainForm.valid).toBeTruthy();
    });

    it("should disable the submit button when the form is invalid", () => {
      const form = component.trainForm;
      form.controls["number"].setValue("");
      form.controls["name"].setValue("Train1");
      form.controls["departureStation"].setValue("");
      form.controls["departureTime"].setValue("11:15 AM");
      form.controls["arrivalStation"].setValue("Banglore");
      form.controls["arrivalTime"].setValue("5:15 AM");
      form.controls["duration"].setValue("6 hrs");
      form.controls["distance"].setValue("750 kms");
      form.controls["fare"].setValue("900");
      form.controls["seatsAvailable"].setValue("15");
      fixture.detectChanges();
      const submitButton = fixture.nativeElement.querySelector(
        'button[type="submit"]'
      );
      expect(submitButton.disabled).toBe(true);
    });

    it("should enable the submit button when the form is valid", () => {
      const form = component.trainForm;
      form.controls["number"].setValue("Train1");
      form.controls["name"].setValue("Train1");
      form.controls["departureStation"].setValue("Hyderabad");
      form.controls["departureTime"].setValue("11:15 AM");
      form.controls["arrivalStation"].setValue("Banglore");
      form.controls["arrivalTime"].setValue("5:15 AM");
      form.controls["duration"].setValue("6 hrs");
      form.controls["distance"].setValue("750 kms");
      form.controls["fare"].setValue("900");
      form.controls["seatsAvailable"].setValue("15");
      fixture.detectChanges();
      const submitButton = fixture.nativeElement.querySelector(
        'button[type="submit"]'
      );
      expect(submitButton.disabled).toBe(false);
    });
  });

  describe("exception", () => {
    it("should invalidate the form when empty", () => {
      component.trainForm.controls["number"].setValue("");
      component.trainForm.controls["name"].setValue("");
      component.trainForm.controls["departureStation"].setValue("");
      component.trainForm.controls["departureTime"].setValue("");
      component.trainForm.controls["arrivalStation"].setValue("");
      component.trainForm.controls["arrivalTime"].setValue("");
      component.trainForm.controls["duration"].setValue("");
      component.trainForm.controls["distance"].setValue("");
      component.trainForm.controls["fare"].setValue("");
      component.trainForm.controls["seatsAvailable"].setValue("");
      expect(component.trainForm.valid).toBeFalsy();
    });

    it("name field should show required error when no value passed", () => {
      const c = component.trainForm.controls["name"];
      expect(c.valid).toBeFalsy();
      c.setValue("");
      expect(c.hasError("required")).toBeTruthy();
    });

    it("number field should show required error when no value passed", () => {
      const c = component.trainForm.controls["number"];
      expect(c.valid).toBeFalsy();
      c.setValue("");
      expect(c.hasError("required")).toBeTruthy();
    });

    it("departureStation field should show required error when no value passed", () => {
      const c = component.trainForm.controls["departureStation"];
      expect(c.valid).toBeFalsy();
      c.setValue("");
      expect(c.hasError("required")).toBeTruthy();
    });

    it("departureTime field should show required error when no value passed", () => {
      const c = component.trainForm.controls["departureTime"];
      expect(c.valid).toBeFalsy();
      c.setValue("");
      expect(c.hasError("required")).toBeTruthy();
    });

    it("arrivalStation field should show required error when no value passed", () => {
      const c = component.trainForm.controls["arrivalStation"];
      expect(c.valid).toBeFalsy();
      c.setValue("");
      expect(c.hasError("required")).toBeTruthy();
    });
    it("arrivalTime field should show required error when no value passed", () => {
      const c = component.trainForm.controls["arrivalTime"];
      expect(c.valid).toBeFalsy();
      c.setValue("");
      expect(c.hasError("required")).toBeTruthy();
    });
    it("duration field should show required error when no value passed", () => {
      const c = component.trainForm.controls["duration"];
      expect(c.valid).toBeFalsy();
      c.setValue("");
      expect(c.hasError("required")).toBeTruthy();
    });

    it("distance field should show required error when no value passed", () => {
      const c = component.trainForm.controls["distance"];
      expect(c.valid).toBeFalsy();
      c.setValue("");
      expect(c.hasError("required")).toBeTruthy();
    });
    it("fare field should not show required error when no value passed", () => {
      const c = component.trainForm.controls["fare"];
      expect(c.valid).toBeTruthy();//
      c.setValue("");
      expect(c.hasError("required")).toBeFalsy();//
    });
    it("seatsAvailable field should not show required error when no value passed", () => {
      const c = component.trainForm.controls["seatsAvailable"];
      expect(c.valid).toBeTruthy();//
      c.setValue("");
      expect(c.hasError("required")).toBeFalsy();//
    });


    it("number field should show required error when no value passed while searching", () => {
      const c = component.searchByNumberForm.controls["number"];
      expect(c.valid).toBeFalsy();
      c.setValue("");
      expect(c.hasError("required")).toBeTruthy();
    });
    it("name field should show required error when no value passed while searching", () => {
      const c = component.searchByNameForm.controls["name"];
      expect(c.valid).toBeFalsy();
      c.setValue("");
      expect(c.hasError("required")).toBeTruthy();
    });
    it("seats field should show required error when no value passed while searching", () => {
      const c = component.searchBySeatsAvalForm.controls["seats"];
      expect(c.valid).toBeFalsy();
      c.setValue("");
      expect(c.hasError("required")).toBeTruthy();
    });

  });

  describe("business", () => {
    it('should fetch all trains', () => {
      component.trains = [];
      jest.spyOn(mockService, 'getAllTrains').mockReturnValue(of([train]));
      component.getAllTrains();
      expect(mockService.getAllTrains).toBeCalledTimes(1);
      expect(component.trains.length).toBeGreaterThan(0);
      expect(Array.isArray(component.trains)).toBe(true);
    })

    it('should delete train by id', () => {
      jest.spyOn(mockService, 'deleteTrain').mockReturnValue(of(train));
      component.deleteTrain(1);
      expect(mockService.deleteTrain).toBeCalledTimes(1);
      expect(mockService.deleteTrain).toBeCalledWith(1);
    })

    it('should get train  by id', () => {
      jest.spyOn(mockService, 'getTrainById')
      component.getTrainById(1);
      expect(mockService.getTrainById).toBeCalledTimes(1);
      expect(mockService.getTrainById).toBeCalledWith(1);
    })
 
  });

});










