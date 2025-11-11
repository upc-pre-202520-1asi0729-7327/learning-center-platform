package com.acme.center.platform.iam.interfaces.rest.transform;

import com.acme.center.platform.iam.domain.model.commands.SignInCommand;
import com.acme.center.platform.iam.interfaces.rest.resources.SignInResource;

/**
 * Assembler to convert a SignInResource to a SignInCommand.
 */
public class SignInCommandFromResourceAssembler {
    /**
     * Converts a SignInResource to a SignInCommand.
     * @param signInResource the SignInResource to convert
     * @return the SignInCommand
     */
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.username(), signInResource.password());
    }
}
