# Verifiable Credential Issuance and Verification Code Challenge

## Overview

This coding challenge involves creating a Verifiable Credential (VC) issuance and verification system using Kotlin and the Ktor framework. The project orchestrates the issuance of a Verifiable Credential and subsequently triggers a verification process. Local testing is facilitated by Docker containers running Waltid services, which offer essential REST APIs for the project's functionality.

## Setting up Waltid Docker Containers and Usage

To provide the required REST APIs for the Verifiable Credential flow, run the following Docker commands to start the Waltid services:


1. Start Waltid Docker Containers:

   ```bash
   # Verifier API
   docker run -p 7002:7002 waltid/verifier-api \
     --webHost=0.0.0.0 \
     --webPort=7002 \
     --baseUrl=http://localhost:7002

   # Issuer API
   docker run -p 7001:7001 waltid/issuer-api \
     --webHost=0.0.0.0 \
     --webPort=7001 \
     --baseUrl=http://localhost:7001
   ```

   These commands start the Verifier and Issuer APIs on ports 7002 and 7001, respectively.

2. Clone the repository:

   ```bash
   git clone https://github.com/rezaiyan/CredentialVerifier.git
   ```

3. Open the project in your preferred Kotlin IDE.

4. Run the `Main.kt` file to execute the Verifiable Credential issuance and verification process.


## Project Structure

The project is organized into several Kotlin files:

- **Main.kt:** Contains the main entry point of the application. It initiates the VC issuance process and then calls the verification process if the issuance is successful.

- **JsonCredential.kt:** Defines the structure of the Verifiable Credential using Kotlin serialization. It also includes a function to dynamically generate JSON data for the VC.

- **JsonCredentialGenerator.kt:** Generates dynamic data for the Verifiable Credential, such as unique identifiers, issuance dates, and expiration dates.

- **Ktor.kt:** Configures the Ktor HTTP client for making HTTP requests, including handling content negotiation and logging.

- **Ext.kt:** Provides extensions and utility functions, including a function to check if an HTTP response is OK.


## Dependencies

The project relies on the following dependencies:

- **Ktor:** A Kotlin framework for building asynchronous servers and clients.
- **Kotlinx.serialization:** A library for serializing and deserializing data objects in Kotlin.
- **Waltid Libraries:** Libraries for handling Verifiable Credentials. (https://github.com/walt-id/waltid-identity)