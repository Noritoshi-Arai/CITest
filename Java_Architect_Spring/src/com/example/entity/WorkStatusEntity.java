package com.example.entity;

public class WorkStatusEntity {
	//�ϐ��̒�`
		private int status;
		private String statusName;
		
		//�R���X�g���N�^�̒�`
		public WorkStatusEntity() {
			status = 0;
			statusName = null;
		}
		
		public WorkStatusEntity(int status, String statusName) {
			this.status = status;
			this.statusName = statusName;
		}
		
		//setter, getter�̒�`
		//status
		public void setStatus(int status) {
			this.status = status;
		}
		
		public int getStatus() {
			return status;
		}
		
		//statusName
		public void setStatusName(String statusName) {
			this.statusName = statusName;
		}
		
		public String getStatusName() {
			return statusName;
		}

}
