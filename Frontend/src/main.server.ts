// src/main.service.ts


import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { appConfig } from './app/app.config'; // Ensure this path is correct

const bootstrap = () => bootstrapApplication(AppComponent, appConfig);

export default bootstrap;
