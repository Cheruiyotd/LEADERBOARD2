package com.mcheru.leaderboard.retrofitutyls;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PostProject {
    @POST("1FAIpQLSfGD7-klepoq3PN0IgVMYeqSZu-23MGWP2kPWdlNdhrfq_anw/formResponse")
    @FormUrlEncoded
    Call<Project> projectEntry(
            @Field("entry.2005620554") String name,
            @Field("entry.1045781291") String mail,
            @Field("entry.1065046570") String address,
            @Field("entry.1166974658") String link);
}
