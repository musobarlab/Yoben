/*
 * Copyright 2019 wuriyanto.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wuriyanto.yoben.jwt

import com.wuriyanto.yoben.utils.Error
import com.wuriyanto.yoben.utils.ErrorMessage
import com.wuriyanto.yoben.utils.Ok
import com.wuriyanto.yoben.utils.Result
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.lang.Exception
import java.security.Key



interface IJwtService {
    
    fun generate(claim: CustomClaim?): Result<String?, ErrorMessage>

    fun validate(accessToken: String?): Result<CustomClaim?, ErrorMessage>
}

class JwtService(private val privateKey: Key, private val publicKey: Key): IJwtService {

    override fun generate(claim: CustomClaim?): Result<String?, ErrorMessage> {
        if(claim == null) return Error(ErrorMessage("claim cannot be empty"))

        val jwt: String = Jwts.builder().setSubject(claim.subject)
                .setIssuer(claim.issuer)
                .setAudience(claim.audience)
                .setExpiration(claim.expiration)
                .setIssuedAt(claim.issuedAt)
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact()
        return Ok(jwt)
    }

    override fun validate(accessToken: String?): Result<CustomClaim?, ErrorMessage> {
        if(accessToken.isNullOrEmpty()) return Error(ErrorMessage("access token cannot be empty"))

        var customClaim: CustomClaim?

        val jwtSplit: List<String> = accessToken.split(" ")

        if(jwtSplit.size < 2 || jwtSplit.size > 2) return Error(ErrorMessage("access token is not valid"))

        val token = jwtSplit[1]

        try {
            val c = Jwts.parser().setSigningKey(this.publicKey).parseClaimsJws(token).body
            customClaim = CustomClaim(c.subject, c.issuer, c.audience, c.expiration)
            customClaim.issuedAt = c.issuedAt
            customClaim.notBefore = c.notBefore
            customClaim.id = customClaim.id
        } catch (e: Exception) {
            return Error(ErrorMessage(e.message.toString()))
        }

        return Ok(customClaim)
    }

}