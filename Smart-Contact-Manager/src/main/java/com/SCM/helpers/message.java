
package com.SCM.helpers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//messages
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class message {

    //msg content and type

    private String content;
    @Builder.Default
    private MessageType type=MessageType.green;

}
