import { Component, ElementRef, OnInit, ViewChild ,Input} from '@angular/core';import { isNull } from '@angular/compiler/src/output/output_ast';
import { CONTEXT_NAME } from '@angular/compiler/src/render3/view/util';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'server';
   ngOnInit():void {
  
  }
  text:string[]=[];
  tex=''
  openNav() {
 
    document.getElementById("mySidenav")!.style.width="250px";
  }
  
   closeNav() {
    document.getElementById("mySidenav")!.style.width="0";
  }
  allmails(){
  
  }
  
  send(){
  document.getElementById("s1")!.className="hello"
  document.getElementById("s2")!.style.display="block";
  
  document.getElementById("s3")!.style.display="inline";
  }
  submit(){
   
    this.tex =(<HTMLInputElement>document.getElementById("s2")).value ;
    document.getElementById("s2")!.style.display="none";
    document.getElementById("s3")!.style.display="none";
    (<HTMLInputElement>document.getElementById("s2")).value=""
    this.text.push(this.tex)
    console.log(this.text)
    
  }
  
  


}
