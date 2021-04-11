import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ConfigurationService} from '../../services/configuration.service';

@Component({
  selector: 'app-main-view',
  templateUrl: './main-view.component.html',
  styleUrls: ['./main-view.component.css']
})
export class MainViewComponent implements OnInit {

  constructor(configurationService: ConfigurationService) {
    this.configurationService = configurationService;
  }

  private configured: boolean;
  private configurationService : ConfigurationService

  ngOnInit(): void {
    this.configured = this.configurationService.isConfigured;
  }

  checkConfiguration(){
    return this.configured;
  }


}
