extends Button


func _on_pressed() -> void:
	get_tree().reload_current_scene()  # Reloads the game scene
	Input.set_mouse_mode(Input.MOUSE_MODE_CAPTURED)  # Lock mouse back to control camera
