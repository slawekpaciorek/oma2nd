import {Component, OnInit} from '@angular/core';
import {ConfigurationService} from '../../services/configuration.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-demo-setup',
  templateUrl: './demo-setup.component.html',
  styleUrls: ['./demo-setup.component.css']
})
export class DemoSetupComponent implements OnInit {

  constructor(private configuration : ConfigurationService, private router: Router) {

  }

  ngOnInit(): void {
    console.log("demo configuration");
    if(this.configuration.isConfigured===false) {
      this.configuration.demoSetup();
    }else{
      this.router.navigate(["login"]);
    }
  }

}
