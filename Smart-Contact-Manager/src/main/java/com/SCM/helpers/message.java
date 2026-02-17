
package com.SCM.helpers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class message {


    private String content;
    @Builder.Default
    private MessageType type=MessageType.green;

}
