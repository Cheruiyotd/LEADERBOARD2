package com.mcheru.leaderboard.retrofitutyls;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PostProject {
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    Call<Project> projectEntry(
            @Field("entry.1824927963") String mail,
            @Field("entry.1877115667") String fName,
            @Field("entry.2006916086") String lName,
            @Field("entry.284483984") String link);
}

