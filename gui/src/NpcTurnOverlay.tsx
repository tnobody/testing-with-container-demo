import React from "react";

export const NpcTurnOverlay = () => {
    return (
        <div className="game-board-overlay">
            <div className={"npc-is-turning game-board-overlay__backdrop"}>
                Computer is turning
            </div>
        </div>
    )
}