Here is the full list with authentication endpoints added. The API uses JWT with Spring Security.

## Auth endpoints (base path: `/api/v1/auth`)

| # | Method | Endpoint | Purpose | Access |
|---|--------|----------|---------|--------|
| 1 | POST | `/api/v1/auth/register` | Register a new account (student/staff) | Public |
| 2 | POST | `/api/v1/auth/login` | Log in and receive access + refresh tokens | Public |
| 3 | POST | `/api/v1/auth/refresh` | Get a new access token using a refresh token | Public (valid refresh token) |
| 4 | POST | `/api/v1/auth/logout` | Invalidate the refresh token | Authenticated |
| 5 | GET | `/api/v1/auth/me` | Get the current user's profile | Authenticated |
| 6 | PUT | `/api/v1/auth/change-password` | Change own password | Authenticated |
| 7 | POST | `/api/v1/auth/forgot-password` | Request a password reset email | Public |
| 8 | POST | `/api/v1/auth/reset-password` | Reset password using the emailed token | Public (valid reset token) |

## Item endpoints (base path: `/api/v1/items`)

| # | Method | Endpoint | Purpose | Access |
|---|--------|----------|---------|--------|
| 9 | POST | `/api/v1/items` | Report a lost or found item | Authenticated |
| 10 | GET | `/api/v1/items` | Retrieve all items (paginated, optional `type`/`status` filters) | Public or authenticated |
| 11 | GET | `/api/v1/items/{id}` | Retrieve a single item | Public or authenticated |
| 12 | GET | `/api/v1/items/search?name=&category=&location=` | Search by name, category and/or location | Public or authenticated |
| 13 | PUT | `/api/v1/items/{id}` | Update item information | Owner or ADMIN |
| 14 | PATCH | `/api/v1/items/{id}/status` | Update recovery status | Owner or ADMIN |
| 15 | DELETE | `/api/v1/items/{id}` | Delete a record | Owner or ADMIN |
| 16 | GET | `/api/v1/items/my` | List items reported by the current user | Authenticated |

## Admin endpoints (base path: `/api/v1/admin`)

| # | Method | Endpoint | Purpose | Access |
|---|--------|----------|---------|--------|
| 17 | GET | `/api/v1/admin/users` | List all users | ADMIN |
| 18 | GET | `/api/v1/admin/users/{id}` | Get one user | ADMIN |
| 19 | PATCH | `/api/v1/admin/users/{id}/role` | Change a user's role | ADMIN |
| 20 | PATCH | `/api/v1/admin/users/{id}/status` | Enable or disable an account | ADMIN |
| 21 | DELETE | `/api/v1/admin/users/{id}` | Delete a user | ADMIN |

## Notes

- **Roles:** `STUDENT`, `STAFF`, `ADMIN` (campus security or the lost-and-found office).
- **Authorization header:** protected calls send `Authorization: Bearer <accessToken>`.
- **Public reads:** if you'd rather keep everything private, require authentication on endpoints 10-12 too. Many campuses leave browsing open so people can check for their item without logging in.
- **Ownership:** PUT, PATCH and DELETE on items check that the caller is the original reporter or an ADMIN. Otherwise they return `403 Forbidden`.
- **Extra response codes:**
    - `401 Unauthorized` for a missing or invalid token
    - `403 Forbidden` for insufficient role or not the owner
    - `409 Conflict` for a duplicate email on registration

**Sample bodies**

```json
// POST /auth/register
{ "fullName": "Jane Banda", "email": "jane@university.edu", "password": "StrongPass#1", "role": "STUDENT" }

// POST /auth/login
{ "email": "jane@university.edu", "password": "StrongPass#1" }

// Login response
{ "accessToken": "eyJ...", "refreshToken": "eyJ...", "tokenType": "Bearer", "expiresIn": 3600 }
```

In a real deployment, ignore the `role` field on public registration and always assign `STUDENT`, so people can't self-register as admins.

I can write the Spring Boot code for any of this, including the security config, JWT filter, entities, services and controllers, if you'd like.