package com.wengzw.blood.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * @author wengzw
 * @date 2022/7/30 10:18
 */
public class AuthUser implements UserDetails {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String mobile;
    private String userName;

    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY) // 密码字段不能返回给前端
    private String password;
    // 按需增加
    // 以逗号隔开的角色标识
    private String role;
    private String token;
    private String email;

    private String openid;
    private String gender;
    private Integer age;
    private String avatar;
    private String idcard;
    private String bloodtype;
    private Long integral;
    private Boolean isDisabled;
    private Boolean isDeleted;
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    // 验证码
    @TableField(exist = false)
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getBloodtype() {
        return bloodtype;
    }

    public void setBloodtype(String bloodtype) {
        this.bloodtype = bloodtype;
    }

    public Long getIntegral() {
        return integral;
    }

    public void setIntegral(Long integral) {
        this.integral = integral;
    }

    public Boolean getIsDisabled() {
        return isDisabled;
    }

    public void setDisabled(Boolean disabled) {
        isDisabled = disabled;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public Integer getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}



