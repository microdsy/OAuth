package com.koal.oauth;

/**
 * accessToken–≈œ¢¿‡
 * @author microdsy
 *
 */
public class AccessToken {
	//ret= &msg= &access_token= &token_type= &refresh_token= &expire_in= &scope= 
	public String ret;
	public String msg;
	public String access_token;
	public String token_type;
	public String refresh_token;
	public String expire_in;
	public String scope;
	
	public AccessToken() {
		super();
	}

	public AccessToken(String ret, String msg, String access_token,
			String token_type, String refresh_token, String expire_in,
			String scope) {
		super();
		this.ret = ret;
		this.msg = msg;
		this.access_token = access_token;
		this.token_type = token_type;
		this.refresh_token = refresh_token;
		this.expire_in = expire_in;
		this.scope = scope;
	}

	public String getRet() {
		return ret;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getExpire_in() {
		return expire_in;
	}

	public void setExpire_in(String expire_in) {
		this.expire_in = expire_in;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

}
