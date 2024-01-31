import { Component, Input } from '@angular/core';
@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.scss']
})
export class DialogComponent {
  @Input() visible: boolean = false;

  showDialog() {
    this.visible = true;
  }
}
