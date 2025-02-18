extends Button

func _on_pressed() -> void:
	get_tree().change_scene_to_file("res://scenes/level/main_menu.tscn")  # Go back to Main Menu
	Input.set_mouse_mode(Input.MOUSE_MODE_VISIBLE)  # Unlock mouse for UI
