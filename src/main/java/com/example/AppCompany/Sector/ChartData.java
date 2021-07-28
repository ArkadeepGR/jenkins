package com.example.AppCompany.Sector;

import java.sql.Date;

public class ChartData {
	
		private Date label;
		private float value;
		
		public ChartData() {
			super();
		}
		
		
		public ChartData(Date label, float value) {
			super();
			this.label = label;
			this.value = value;
		}
		
		public Date getLabel() {
			return label;
		}
		public void setLable(Date label) {
			this.label = label;
		}
		public float getValue() {
			return value;
		}
		public void setValue(float value) {
			this.value = value;
		}
		
		
}
