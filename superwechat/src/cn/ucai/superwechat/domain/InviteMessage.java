/**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.ucai.superwechat.domain;

public class InviteMessage {
	private String from;
	private long time;
	private String reason;

	private InviteMesageStatus status;
	private String groupId;
	private String groupName;
	private String groupInviter;
	
	private String usernick;
	private String avatarSuffix;
	private String avatarTime;

	public String getUsernick() {
		return usernick;
	}

	public void setUsernick(String usernick) {
		this.usernick = usernick;
	}

	public String getAvatarSuffix() {
		return avatarSuffix;
	}

	public void setAvatarSuffix(String avatarSuffix) {
		this.avatarSuffix = avatarSuffix;
	}

	public String getAvatarTime() {
		return avatarTime;
	}

	public void setAvatarTime(String avatarTime) {
		this.avatarTime = avatarTime;
	}

	private int id;
	
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}


	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public InviteMesageStatus getStatus() {
		return status;
	}

	public void setStatus(InviteMesageStatus status) {
		this.status = status;
	}

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public void setGroupInviter(String inviter) {
	    groupInviter = inviter;
	}
	
	public String getGroupInviter() {
	    return groupInviter;	    
	}



	public enum InviteMesageStatus{
	    
	    //==contact
		/**being invited*/
		BEINVITEED,
		/**being refused*/
		BEREFUSED,
		/**remote user already agreed*/
		BEAGREED,
		
		//==group application
		/**remote user apply to join*/
		BEAPPLYED,
		/**you have agreed to join*/
		AGREED,
		/**you refused the join request*/
		REFUSED,
		
		//==group invitation
		/**received remote user's invitation**/
		GROUPINVITATION,
		/**remote user accept your invitation**/
		GROUPINVITATION_ACCEPTED,
        /**remote user declined your invitation**/
		GROUPINVITATION_DECLINED
	}
	public String getAvatar() {
		String path = "http://101.251.196.90:8000/SuperWeChatServerV2.0/downloadAvatar?name_or_hxid="+getFrom()+"&avatarType=user_avatar&m_avatar_suffix="+getAvatarSuffix()+"&updatetime="+getAvatarTime();
		return path;
	}

	@Override
	public String toString() {
		return "InviteMessage{" +
				"from='" + from + '\'' +
				", time=" + time +
				", reason='" + reason + '\'' +
				", status=" + status +
				", groupId='" + groupId + '\'' +
				", groupName='" + groupName + '\'' +
				", groupInviter='" + groupInviter + '\'' +
				", usernick='" + usernick + '\'' +
				", avatarSuffix='" + avatarSuffix + '\'' +
				", avatarTime='" + avatarTime + '\'' +
				", id=" + id +
				'}';
	}
}



