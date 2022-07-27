import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../../services/AdminService';

@Component({
  selector: 'app-start-repartition',
  templateUrl: './start-repartition.component.html',
  styleUrls: ['./start-repartition.component.css']
})
export class StartRepartitionComponent implements OnInit {

  constructor(private adminManager: AdminService) { }

  ngOnInit(): void {
  }

  onStartRepartition(){
    this.adminManager.startRepartitionProcess().subscribe(
      (response)=>{
        alert("Procesul de repartitie a fost finalizat!") 
      }
    )
  }
}
