import { Component, signal } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

interface BillingRun {
  billingRunId: number;
  runName: string;
  periodFrom: string;
  periodTo: string;
  status: string;
  createdAt: string;
}

interface BillingItem {
  billingItemId: number;
  billingRunId: number;
  customerId: number;
  mpId: number;
  tariffId: number;
  totalConsumption: number;
  pricePerKwh: number;
  baseFee: number;
  energyAmount: number;
  totalAmount: number;
  status: string;
  createdAt: string;
}

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, HttpClientModule, FormsModule],
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class App {
  billingRuns = signal<BillingRun[]>([]);
  billingItems = signal<BillingItem[]>([]);

  message = signal<string>('');

  newRun = {
    runName: '',
    periodFrom: '',
    periodTo: '',
    createdBy: 22
  };

  constructor(private http: HttpClient) {
    this.loadBillingRuns();
    this.loadBillingItems();
  }

  loadBillingRuns() {
    this.http
      .get<BillingRun[]>('https://api.amjadalahdal.com/api/billing-runs')
      .subscribe(data => this.billingRuns.set(data));
  }

  loadBillingItems() {
    this.http
      .get<BillingItem[]>('https://api.amjadalahdal.com/api/billing-items')
      .subscribe(data => this.billingItems.set(data));
  }

  processRun(id: number) {
    this.http
      .post<{ message: string }>(
        `https://api.amjadalahdal.com/api/billing-runs/${id}/process?createdBy=22`,
        {}
      )
      .subscribe(() => {
        this.loadBillingRuns();
        this.loadBillingItems();
      });
  }

  finalizeRun(id: number) {
    this.http
      .post<{ message: string }>(
        `https://api.amjadalahdal.com/api/billing-runs/${id}/finalize`,
        {}
      )
      .subscribe(() => {
        this.loadBillingRuns();
        this.loadBillingItems();
      });
  }

  createBillingRun() {
    this.message.set('');

    const request = {
      runName: this.newRun.runName,
      periodFrom: this.toApiDate(this.newRun.periodFrom),
      periodTo: this.toApiDate(this.newRun.periodTo),
      createdBy: this.newRun.createdBy
    };

    this.http
      .post<{ message: string }>(
        'https://api.amjadalahdal.com/api/billing-runs',
        request
      )
      .subscribe({
        next: (response) => {
          this.message.set(response.message);
          this.loadBillingRuns();
          this.loadBillingItems();

          this.newRun = {
            runName: '',
            periodFrom: '',
            periodTo: '',
            createdBy: 22
          };
        },
        error: () => {
          this.message.set('Could not create billing run.');
        }
      });
  }

  toApiDate(value: string): string {
    const [day, month, year] = value.split('.');
    return `${year}-${month}-${day}`;
  }
}
