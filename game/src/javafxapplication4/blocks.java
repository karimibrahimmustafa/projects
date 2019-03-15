/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

import static java.lang.Math.abs;

/**
 *
 * @author kimo only
 */
public class blocks{
 
 double x1=0;
 double x2=0;
 double y1=0;
 double y2=0;
 double width=0;
 double height=0;

    blocks(double i, double i0,double i1, double i2,double width, double height) {
this.x1=i;
  this.x2=i0;
 this.y1=i1;
 this.y2=i2;    
  this.width=width;
  this.height=height;
    }
 public void set(double x1,double x2,double x3, double x4,double width, double height){
 this.x1=x1;
  this.x2=x2;
 this.y1=x3;
 this.y2=x4;
this.width=width;
  this.height=height;
 }
 public boolean left(double x,double y){
 if(((abs(x-x1)-18))<5)
     return false;
return true;
 
 }
 public boolean right(double x,double y){
 if((abs(x-x2)-19)<5)
     return false;
return true;
 
 }
 public boolean top(double x,double y){
 if((abs(y-y1)-19)<5)
 {return false;}
return true;
 
 }
 public boolean down(double x,double y){
 if((abs(y-y2)-19)<5)
 {return false;}
return true;
 
 }
 public boolean downlimit(double x,double y){
 if(y>y1-20&&y<y2+20)
 {return false;}
return true;
 
 }
 public boolean leftlimit(double x,double y){
 if(x>x1-20&&x<x2+20)
 {return false;}
return true;
 
 }
 
  public boolean downlimit2(double x,double y){
 if(y>y1-33&&y<y2+30)
 {return true;}
return false;
 
 }
 public boolean leftlimit2(double x,double y){
 if(x>x1-33&&x<x2+30)
 {return true;}
return false;
 
 }
 }